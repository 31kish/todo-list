package jp.co.keishikuwabara.todo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.keishikuwabara.todo.R
import jp.co.keishikuwabara.todo.SwipeCallback
import jp.co.keishikuwabara.todo.adapter.TodoAdapter
import jp.co.keishikuwabara.todo.model.TodoEntity
import jp.co.keishikuwabara.todo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(activity)

        recycler_view.adapter = TodoAdapter(viewModel.todoList, object : TodoAdapter.TodoListener {
            override fun onClickRow(view: View, rowData: TodoEntity) {
                onClickedRow(view, rowData)
            }

            override fun onCheck(isChecked: Boolean, position: Int) {
                onChecked(isChecked, position)
            }

            override fun onLongClick(view: View, rowData: TodoEntity) {
                onLongClicked(view, rowData)
            }
        })

        val swipeHandler = object : SwipeCallback(activity!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                this@MainFragment.onSwiped(viewHolder, direction)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recycler_view)

        plus_button.setOnClickListener {
            val fragment = ContentFragment()
            fragment.viewModel = viewModel

            val tx = activity?.supportFragmentManager?.beginTransaction()
            tx?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            tx?.addToBackStack("")
            tx?.replace(R.id.container, fragment, ContentFragment.TAG)
            tx?.commit()
        }
    }

    private fun onClickedRow(view: View, rowData: TodoEntity) {
        Log.i(TAG, "onClicked")
        Log.i(TAG, rowData.content)
        Log.i(TAG, rowData.isChecked.toString())
    }

    private fun onChecked(isChecked: Boolean, position: Int) {
        viewModel.todoList[position].isChecked = isChecked
        recycler_view.adapter.notifyItemChanged(position)
    }

    private fun onLongClicked(view: View, rowData: TodoEntity) {
        Log.i(TAG, "onLongClicked")
        Log.i(TAG, rowData.content)
        Log.i(TAG, rowData.isChecked.toString())
    }

    private fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        viewHolder?.let {
            removeAt(it.adapterPosition)
        }
    }

    private fun removeAt(position: Int) {
        viewModel.removeAt(position)
        recycler_view.adapter.notifyItemRemoved(position)
    }
}