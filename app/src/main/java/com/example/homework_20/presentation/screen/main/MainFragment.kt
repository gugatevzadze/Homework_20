package com.example.homework_20.presentation.screen.main

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework_20.databinding.FragmentMainBinding
import com.example.homework_20.presentation.base.BaseFragment
import com.example.homework_20.presentation.event.main.MainEvents
import com.example.homework_20.presentation.model.user.UserModel
import com.example.homework_20.presentation.state.main.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.UUID

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun setUp() {
        onCountViewSetText()
    }

    override fun viewActionListeners() {
        onAddButtonClickListener()
        onDeleteButtonClickListener()
        onUpdateButtonClickListener()
    }

    override fun bindObservers() {
        observeMessage()
    }

    private fun observeMessage() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userState.collect { state ->
                    handleState(state)
                }
            }
        }
    }

    private fun handleState(state: MainState) {
        viewModel.onEvent(MainEvents.GetUsers)
        binding.tvActiveUsers.text = state.userCount?.toString()
        state.errorMessage?.let {
            binding.tvError.text = it
            binding.tvError.visibility = View.VISIBLE
            binding.tvSuccess.visibility = View.GONE
            viewModel.onEvent(MainEvents.ResetMessage)
        }
        state.statusMessage?.let {
            binding.tvSuccess.text = it
            binding.tvSuccess.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            viewModel.onEvent(MainEvents.ResetMessage)
        }
    }

    private fun getUserDetails(): UserModel {
        val email = binding.etEmail.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()

        return UserModel(email, firstName, lastName, age)
    }

    private fun addUser() {
        val user = getUserDetails()
        viewModel.onEvent(MainEvents.AddUser(user))
    }

    private fun deleteUser() {
        val user = getUserDetails()
        viewModel.onEvent(MainEvents.DeleteUser(user))
    }

    private fun updateUser() {
        val user = getUserDetails()
        viewModel.onEvent(MainEvents.UpdateUser(user))
    }

    private fun countUser() {
        viewModel.onEvent(MainEvents.GetUserCount)
    }

    private fun onAddButtonClickListener() {
        binding.btnAdd.setOnClickListener {
            addUser()
        }
    }

    private fun onDeleteButtonClickListener() {
        binding.btnDelete.setOnClickListener {
            deleteUser()
        }
    }

    private fun onUpdateButtonClickListener() {
        binding.btnUpdate.setOnClickListener {
            updateUser()
        }
    }

    private fun onCountViewSetText(){
        binding.tvActiveUsers.text = countUser().toString()
    }
}