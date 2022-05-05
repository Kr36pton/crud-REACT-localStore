import React, { useState, useEffect } from "react";
const Store = React.createContext();
// El endpoint
const HOST_API = "http://localhost:8080";
function TodoContext(props) {
  const [state, setState] = useState([{}]);
  const [searchValue, setSearchValue] = useState("");
  let completados = 0;
  //Revisa los Todo que esten completados
  state.forEach((element) => {
    if (element.completed === true) {
      completados++;
    }
  });
  //Asigna el total de completados
  const completedTodos = completados;
  completados = 0;
  const totalTodos = state.length;
  //Estado inicial del buscador
  let searchedTodos = [{}];
  //Busqueda de elementos en la lista
  if (!searchValue.length >= 1) {
    searchedTodos = state;
  } else {
    searchedTodos = state?.filter((todo) => {
      const todoText = todo.text.toLowerCase();
      const searchText = searchValue.toLowerCase();
      return todoText.includes(searchText);
    });
  }
  //Guarda un nuevo elemento
  const saveTodos = (request) => {
    fetch(HOST_API + "/todos/add", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((response) => response.json());
  };
  //Agrega el request para guardar un elemento
  const addTodo = (newTodo) => {
    const newRequest = {
      completed: false,
      text: newTodo,
    };
    saveTodos(newRequest);
  };
  //Actualiza el estado del Todo a completado
  const completeTodo = (todo) => {
    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        id: todo.id,
        completed: true,
        text: todo.text,
      }),
    };
    fetch(HOST_API + "/todos/updatetodo/" + todo.id, requestOptions).then(
      (response) => response.json()
    );
  };
  //Borra elementos de la lista
  const deleteTodo = (identifier) => {
    fetch(HOST_API + "/todos/eliminar-todos/" + identifier, {
      method: "DELETE",
    });
  };

  //Hook para listar los elementos
  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(HOST_API + "/todos");
      const newData = await response.json();
      setState(newData);
    };
      fetchData();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    , [state.length, state]);

  return (
    <Store.Provider
      value={{
        totalTodos,
        completedTodos,
        searchValue,
        setSearchValue,
        searchedTodos,
        addTodo,
        completeTodo,
        deleteTodo,
      }}
    >
      {props.children}
    </Store.Provider>
  );
}
export { TodoContext, Store };