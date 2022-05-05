import React from 'react';
import { store } from './TodoContext';
import '../Styles/TodoCounter.css';

function TodoCounter() {
  const { totalTodos, completedTodos } = React.useContext(Store);
  
  return (
    <h2 className="TodoCounter">HAS COMPLETADO {completedTodos} DE {totalTodos} DEBERES</h2>
  );
}

export { TodoCounter };
