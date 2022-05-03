import React from 'react';
import { TodoContext } from './TodoContext';
import '../Styles/TodoForm.css';
import Swal from 'sweetalert2';

function TodoForm() {
  const [newTodoValue, setNewTodoValue] = React.useState('');
  const {
    addTodo,
    setOpenModal,
  } = React.useContext(TodoContext);
  
  const onChange = (event) => {
    setNewTodoValue(event.target.value);
  };
  const onCancel = () => {
    setOpenModal(false);
  };
  const onSubmit = (event) => {
    event.preventDefault();
    addTodo(newTodoValue);
    setOpenModal(false);

    Swal.fire({
      icon: 'success',
      title: 'SE HA CREADO UN NUEVO DEBER'
    })
    
  };

  return (
    <form onSubmit={onSubmit}>
      <label>ESCRIBE TU NUEVO DEBER</label>
      <textarea
        value={newTodoValue}
        onChange={onChange}
        placeholder="Comprar pan"
      />
      <div className="TodoForm-buttonContainer">
        <button
          type="button"
          className="TodoForm-button TodoForm-button--cancel"
          onClick={onCancel}
          >
          Cancelar
        </button>
        <button
          type="submit"
          className="TodoForm-button TodoForm-button--add"
        >
          Añadir
        </button>
      </div>
    </form>
  );
}

export { TodoForm };
