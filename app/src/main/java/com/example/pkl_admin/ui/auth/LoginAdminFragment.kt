package com.example.pkl_admin.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pkl_admin.R
import com.example.pkl_admin.databinding.FragmentLoginAdminBinding
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty



class LoginAdminFragment : Fragment() {
    private var _binding: FragmentLoginAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        checkLogin()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDLoginBtnLogin.setOnClickListener {
            val email = binding.IDLoginEdtEmail.text.toString()
            val password = binding.IDLoginEdtPassword.text.toString()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful){
                    findNavController().navigate(R.id.action_loginAdminFragment_to_dashboardFragment)
                    Toasty.info(
                        requireContext(),
                        "Welcome",
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                }else{
                    Toasty.error(
                        requireContext(),
                        "" + it.exception?.message,
                        Toast.LENGTH_SHORT,
                        true
                    ).show()
                }

            }.addOnFailureListener {
                Toasty.error(
                    requireContext(),
                    "" + it.message ,Toast.LENGTH_SHORT,
                    true
                ).show()
            }
        }
    }
private fun checkLogin(){
    val user = FirebaseAuth.getInstance().currentUser?.uid
    if (user!=null){
        findNavController().navigate(R.id.action_loginAdminFragment_to_dashboardFragment)
    }
}
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}