package com.example.dataapi.presenter

import com.example.dataapi.UpdateAddActivity
import com.example.dataapi.model.ResultStatus
import com.example.dataapi.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter2(val crudView: UpdateAddActivity) {
    fun addData(name: String, hp: String, alamat: String) {
        NetworkConfig.getService()
            .addStaff(name, hp, alamat)
            .enqueue(object: retrofit2.Callback<ResultStatus>{
                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if(response.isSuccessful && response.body()?.status?.toIntOrNull() == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorAdd(response.body()?.pesan ?: "")
                    }
                }

                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAdd(t.localizedMessage)
                }

            })
    }

    fun updateData(id: String, name: String, hp: String, alamat: String) {
        NetworkConfig.getService()
            .updateStaff(id, name, hp, alamat)
            .enqueue(object: retrofit2.Callback<ResultStatus>{
                override fun onResponse(
                    call: Call<ResultStatus>,
                    response: Response<ResultStatus>
                ) {
                    if(response.isSuccessful && response.body()?.status?.toIntOrNull() == 200) {
                        crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                    } else {
                        crudView.onFailedUpdate(response.body()?.pesan ?: "")
                    }
                }

                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage)
                }

            })
    }
}