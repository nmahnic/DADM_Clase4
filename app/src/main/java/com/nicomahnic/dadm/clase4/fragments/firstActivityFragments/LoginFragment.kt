package com.nicomahnic.dadm.clase4.fragments.firstActivityFragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nicomahnic.dadm.clase4.entities.User
import com.nicomahnic.dadm.clase4.R
import com.nicomahnic.dadm.clase4.activities.SecondActivity
import com.nicomahnic.dadm.clase4.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    var btnUser : Boolean = false
    var btnPasswd : Boolean = false

    lateinit var v : View

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        v = view

        binding.edtUser.apply { addTextChangedListener(userWatcher) }

        binding.edtPasswd.apply { addTextChangedListener(passwdWatcher) }

        binding.btnEnter.isEnabled = false
    }

    private val userWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            println("afterTextChanged -> $s")

            btnUser = s.toString().isNotEmpty()
            binding.btnEnter.isEnabled = btnUser && btnPasswd

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private val passwdWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            println("afterTextChanged -> $s")

            btnPasswd = s.toString().isNotEmpty()
            binding.btnEnter.isEnabled = btnUser && btnPasswd

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onStart() {
        super.onStart()

        val userList: MutableList<User> = ArrayList<User>()
        userList.add(User("Mash", "1234"))
        userList.add(User("Juanma", "1234"))
        userList.add(User("Juani", "1234"))
        userList.add(User("Eric", "1234"))
        userList.add(User("Tiago", "1234"))


        binding.btnEnter.setOnClickListener {
            val validUser = userList.find{it.name == binding.edtUser.text.toString()}

            validateUser(validUser)?.let{
                val sendIntent = Intent(context, SecondActivity::class.java)
                sendIntent.putExtra(Intent.EXTRA_TEXT, validUser!!.name)
                startActivity(sendIntent)
                requireActivity().finish()
            }
        }
    }

    private fun validateUser(validUser: User?) : Boolean? {
        validUser?.let{ user ->
            if(user.passwd == binding.edtPasswd.text.toString()) user.checked = true
            if(user.checked) {
                return true
            }else{
                Snackbar.make(v, "Password no valida", Snackbar.LENGTH_SHORT).show()
            }
        } ?: run {
            Snackbar.make(v, "Usuario no registrado", Snackbar.LENGTH_SHORT).show()
        }
        return null
    }
}