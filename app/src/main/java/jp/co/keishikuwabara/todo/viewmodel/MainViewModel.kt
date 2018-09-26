package jp.co.keishikuwabara.todo.viewmodel

import jp.co.keishikuwabara.todo.model.TodoEntity

class MainViewModel {
    private var mutableTodoList: MutableList<TodoEntity> = mutableListOf()
    var todoList: List<TodoEntity> = listOf()
        get() = this.mutableTodoList

    fun add(entity: TodoEntity) {
        mutableTodoList.add(entity)
    }

    fun removeAt(index: Int) {
        mutableTodoList.removeAt(index)
    }
}