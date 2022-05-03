import React from 'react';
import '../Styles/TodoItem.css';

function TodoItem(props) {
  return (
    <li className="TodoItem">
      <span
        className={`Icon Icon-check ${props.completed && 'Icon-check--active'}`}
        onClick={props.onComplete}
      >
        <i class="fa-solid fa-face-grin-beam"></i>
      </span>
      <p className={`TodoItem-p ${props.completed && 'TodoItem-p--complete'}`}>
        {props.text}
      </p>
      <span
        className="Icon Icon-delete"
        onClick={props.onDelete}
      >
        <i class="fa-solid fa-circle-xmark"></i>
      </span>
    </li>
  );
}

export { TodoItem };
