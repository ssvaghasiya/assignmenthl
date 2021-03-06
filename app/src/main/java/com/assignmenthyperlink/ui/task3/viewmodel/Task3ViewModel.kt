package com.assignmenthyperlink.ui.task3.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import com.assignmenthyperlink.apputils.Debug
import com.assignmenthyperlink.base.viewmodel.BaseViewModel
import com.assignmenthyperlink.databinding.ActivityTask3Binding
import com.assignmenthyperlink.interfaces.CallbackListener
import com.assignmenthyperlink.ui.home.datamodel.MemeData
import com.assignmenthyperlink.ui.home.datamodel.MemeDataModel
import com.assignmenthyperlink.ui.task3.datamodel.Task3Data
import com.assignmenthyperlink.ui.task3.utils.LeftDataAdapter
import com.assignmenthyperlink.ui.task3.utils.RightDataAdapter

class Task3ViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityTask3Binding
    private lateinit var mContext: Context
    lateinit var memeDataModel: MemeDataModel
    lateinit var leftDataAdapter: LeftDataAdapter
    lateinit var rightDataAdapter: RightDataAdapter


    fun setBinder(binder: ActivityTask3Binding) {
        this.binder = binder
        this.mContext = binder.root.context
        this.binder.viewModel = this
        this.binder.viewClickHandler = ViewClickHandler()
        memeDataModel = MemeDataModel()
        init()
    }

    private fun init() {
        leftDataAdapter = LeftDataAdapter(mContext)
        binder.rvLeft.adapter = leftDataAdapter

        leftDataAdapter.setEventListener(object : LeftDataAdapter.EventListener {
            override fun onItemClick(pos: Int, item: Task3Data) {

            }

            override fun onItemSelectedClick(pos: Int, item: Task3Data) {

            }

            override fun onItemNotSelectedClick(pos: Int, item: Task3Data) {

            }

        })

        rightDataAdapter = RightDataAdapter(mContext)
        binder.rvRight.adapter = rightDataAdapter

        rightDataAdapter.setEventListener(object : RightDataAdapter.EventListener {
            override fun onItemClick(pos: Int, item: Task3Data) {

            }

            override fun onItemSelectedClick(pos: Int, item: Task3Data) {

            }

            override fun onItemNotSelectedClick(pos: Int, item: Task3Data) {

            }
        })

    }

    fun makeTaskData(name: String): Task3Data {
        var task = Task3Data()
        task.name = name
        task.isSelected = false
        return task
    }

    inner class ViewClickHandler {
        fun onAdd(view: View) {
            try {
//                var findLeft: Task3Data? = leftDataAdapter.getDataLeft()
//                    .find { it.name == binder.tvName.text.toString().trim() }

//                if (findLeft == null && binder.tvName.text.toString().trim().isNullOrEmpty()
//                        .not()
//                ) {
//                    leftDataAdapter.add(makeTaskData(binder.tvName.text.toString().trim()))
//                }
                var findRight: Task3Data? = rightDataAdapter.getDataRight()
                    .find { it.name == binder.tvName.text.toString().trim() }
                if (findRight == null && binder.tvName.text.toString().trim().isNullOrEmpty()
                        .not()
                ) {
                    rightDataAdapter.add(makeTaskData(binder.tvName.text.toString().trim()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onDelete(view: View) {
            try {
                leftDataAdapter.getDataLeft().forEachIndexed { index, element ->
                    if (element.name == binder.tvName.text.toString().trim()) {
                        leftDataAdapter.removeAt(index)
                    }
                }
                rightDataAdapter.getDataRight().forEachIndexed { index, element ->
                    if (element.name == binder.tvName.text.toString().trim()) {
                        rightDataAdapter.removeAt(index)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onCopyRight(view: View) {
            try {
                for (i in leftDataAdapter.getDataLeft()) {
                    var findData: Task3Data? = rightDataAdapter.getDataRight()
                        .find { it.name == i.name }
                    if (i.isSelected && findData == null) {
                        rightDataAdapter.add(i)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onCopyLeft(view: View) {
            try {
                for (i in rightDataAdapter.getDataRight()) {
                    var findData: Task3Data? = leftDataAdapter.getDataLeft()
                        .find { it.name == i.name }
                    if (i.isSelected && findData == null) {
                        leftDataAdapter.add(i)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onMoveRight(view: View) {
            try {
//                leftDataAdapter.getDataLeft().forEachIndexed { index, element ->
//                    var findData: Task3Data? = rightDataAdapter.getDataRight()
//                        .find { it.name == element.name }
//                    if (element.isSelected && findData == null) {
//                        rightDataAdapter.add(element)
//                        leftDataAdapter.removeAt(index)
//                    }
//                }

                val iterator: MutableIterator<Task3Data>? = leftDataAdapter.getDataLeft().iterator()
                while (iterator?.hasNext() == true) {
                    val value = iterator.next()
                    var findData: Task3Data? = rightDataAdapter.getDataRight()
                        .find { it.name == value.name }
                    if (value.isSelected && findData == null) {
                        rightDataAdapter.add(value)
                        iterator.remove()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun onMoveLeft(view: View) {
            try {
//                rightDataAdapter.getDataRight().forEachIndexed { index, element ->
//                    var findData: Task3Data? = leftDataAdapter.getDataLeft()
//                        .find { it.name == element.name }
//                    if (element.isSelected && findData == null) {
//                        leftDataAdapter.add(element)
//                        rightDataAdapter.removeAt(index)
//                    }
//                }
                val iterator: MutableIterator<Task3Data>? =
                    rightDataAdapter.getDataRight().iterator()
                while (iterator?.hasNext() == true) {
                    val value = iterator.next()
                    var findData: Task3Data? = leftDataAdapter.getDataLeft()
                        .find { it.name == value.name }
                    if (value.isSelected && findData == null) {
                        leftDataAdapter.add(value)
                        iterator.remove()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        fun onSwap(view: View) {
            try {
                var tempRight = mutableListOf<Task3Data>()
                rightDataAdapter.getDataRight().forEachIndexed { index, element ->
                    if (element.isSelected) {
                        tempRight.add(element)
                        if (tempRight.size > 1) {
                            showToast("More than Two item Selcted at right side")
                            return@forEachIndexed
                        }
                    }
                }
                var tempLeft = mutableListOf<Task3Data>()
                leftDataAdapter.getDataLeft().forEachIndexed { index, element ->
                    if (element.isSelected) {
                        tempLeft.add(element)
                        if (tempLeft.size > 1) {
                            showToast("More than Two item Selcted at left side")
                            return@forEachIndexed
                        }
                    }
                }

                if (tempLeft.size == 1 && tempRight.size == 1) {
//                    val iterator: MutableIterator<Task3Data>? =
//                        rightDataAdapter.getDataRight().iterator()
//                    while (iterator?.hasNext() == true) {
//                        val value = iterator.next()
//                        var findData: Task3Data? = leftDataAdapter.getDataLeft()
//                            .find { it.name == value.name }
//                        if (value.isSelected && findData == null) {
//                            leftDataAdapter.add(value)
//                            iterator.remove()
//                        }
//                    }
//
//                    val iterator1: MutableIterator<Task3Data>? = leftDataAdapter.getDataLeft().iterator()
//                    while (iterator1?.hasNext() == true) {
//                        val value = iterator1.next()
//                        var findData: Task3Data? = rightDataAdapter.getDataRight()
//                            .find { it.name == value.name }
//                        if (value.isSelected && findData == null) {
//                            rightDataAdapter.add(value)
//                            iterator1.remove()
//                        }
//                    }

                    leftDataAdapter.getDataLeft().forEachIndexed { index, element ->
                        var findData: Task3Data? = rightDataAdapter.getDataRight()
                            .find { it.name == element.name }
                        if (element.isSelected && findData == null) {
                            rightDataAdapter.add(element)
                            leftDataAdapter.removeAt(index)
                        }
                    }

                    rightDataAdapter.getDataRight().forEachIndexed { index, element ->
                        var findData: Task3Data? = leftDataAdapter.getDataLeft()
                            .find { it.name == element.name }
                        if (element.isSelected && findData == null) {
                            leftDataAdapter.add(element)
                            rightDataAdapter.removeAt(index)
                        }
                    }

                } else {
                    showToast("Not Selected")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
