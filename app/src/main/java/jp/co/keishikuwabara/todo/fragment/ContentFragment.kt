package jp.co.keishikuwabara.todo.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.keishikuwabara.todo.R
import jp.co.keishikuwabara.todo.model.TodoEntity
import jp.co.keishikuwabara.todo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.fragment_main.*

class ContentFragment : Fragment() {
    companion object {
        const val TAG = "ContentFragment"
    }

    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_button.setOnClickListener {
            viewModel.add(TodoEntity())
            recycler_view.adapter.notifyItemInserted(viewModel.todoList.lastIndex)
            recycler_view.scrollToPosition(viewModel.todoList.lastIndex)
            activity?.supportFragmentManager?.popBackStack()
        }

        cancel_button.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}


/*
*     private fun create() {
        viewModel.add(TodoEntity())
        recycler_view.adapter.notifyItemInserted(viewModel.todoList.lastIndex)
        recycler_view.scrollToPosition(viewModel.todoList.lastIndex)
    }*/