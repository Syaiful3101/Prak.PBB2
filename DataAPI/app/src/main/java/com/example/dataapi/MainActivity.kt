package com.example.dataapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dataapi.adapter.DataAdapter
import com.example.dataapi.databinding.ActivityMainBinding
import com.example.dataapi.model.DataItem
import com.example.dataapi.presenter.Presenter
import java.util.List

class MainActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter(this)
        presenter.getData()

        binding.btnTambah.setOnClickListener {
            startActivity(Intent(applicationContext, UpdateAddActivity::class.java))
            finish()
        }

    }

    override fun onSuccessGet(data: List<DataItem>?) {
        binding.rvCategory.adapter = DataAdapter(data, object : DataAdapter.onClickItem{
            override fun clicked(item: DataItem?) {
                val bundle = Bundle()
                bundle.putSerializable("dataItem", item)
                val intent = Intent(applicationContext, UpdateAddActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.staffId)
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }

        })
    }

    override fun successAdd(msg: String) { }

    override fun onErrorAdd(msg: String) { }

    override fun onFailedGet(msg: String) { }

    override fun onSuccessUpdate(msg: String) { }

    override fun onErrorUpdate(msg: String) { }

    override fun onSuccessDelete(msg: String) {
        presenter.getData()
    }

    override fun onErrorDelete(msg: String) {
        Toast.makeText(this, "Delete Tidak Berhasil", Toast.LENGTH_SHORT).show()
    }
    override fun onFailedUpdate(msg: String) { }

    }