package com.example.datateman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.datateman.databinding.ActivityLoginBinding
import com.example.datateman.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var auth:FirebaseAuth? = null
    private val RC_SING_IN = 1
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inisiasi 10 (button)
        binding.logout.setOnClickListener(this)
        binding.save.setOnClickListener(this)
        binding.showData.setOnClickListener(this)

        //mendapatkan instance firebase authentikasi
        auth = FirebaseAuth.getInstance()
    }
    private fun isEmpty(s: String): Boolean{
        return TextUtils.isEmpty(s)
    }

    override fun onClick(p0: View?) {
        when (p0?.getId()){
            R.id.save -> { // Statement program untuk simpan data
                // mendapatkan userid dari pengguna yang terauthentikasi
                val getUserID = auth!!.currentUser!!.uid
                // mendapatkan instance dari database
                val database = FirebaseDatabase.getInstance()
                // menyimpan data yang diinputkan user ke dalam variabel
                val getNama: String = binding.nama.getText().toString()
                val getAlamat: String = binding.alamat.getText().toString()
                val getNoHP: String = binding.noHp.getText().toString()
                // mendapatkan referensi dari database
                val getReference: DatabaseReference
                getReference = database.reference
                // mengecek apakah ada data yang kosong
                if (isEmpty(getNama) || isEmpty(getAlamat) || isEmpty(getNoHP)){
                    // jika ada, maka akan menmpilkan pesan singkat seperti berikut ini
                    Toast.makeText(this@MainActivity, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
                } else {
                    // jika tidak ada, maka menyimpan ke database sesuai id masing masing akun
                    getReference.child("Admin").child(getUserID).child("DataTeman").push()
                        .setValue(data_teman(getNama, getAlamat, getNoHP))
                        .addOnCompleteListener(this) {
                            //bagian ini terjadi ketika user berhasil menympan data
                            binding.nama.setText("")
                            binding.alamat.setText("")
                            binding.noHp.setText("")
                            Toast.makeText(this@MainActivity, "Data tersimpan", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            R.id.logout -> { // Statement program untuk logout/keluar
                AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(object  : OnCompleteListener<Void> {
                        override fun onComplete(p0: Task<Void>){
                            Toast.makeText(this@MainActivity, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                            intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
            }
            R.id.showData -> {
                startActivity(Intent(this@MainActivity, MyListData::class.java))
            }
        }
    }
}