package com.example.dataapi;

import com.example.dataapi.model.DataItem
import java.util.List;

interface CrudView {

        fun successAdd(msg: String)
        fun onErrorAdd(msg: String)

        fun onSuccessGet(data: List<DataItem>?)
        fun onFailedGet(msg: String)

        fun onSuccessUpdate(msg: String)
        fun onErrorUpdate(msg: String)

        fun onSuccessDelete(msg: String)
        fun onErrorDelete(msg: String)
        fun onFailedUpdate(msg: String)

}