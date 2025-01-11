// API endpoints
const API_BASE_URL = '/api/todos';

// DOM elements
const todoForm = document.getElementById('todoForm');
const todoList = document.getElementById('todoList');

// Fetch all todos
async function fetchTodos() {
    try {
        const response = await fetch(API_BASE_URL);
        const todos = await response.json();
        renderTodos(todos);
    } catch (error) {
        console.error('Error fetching todos:', error);
    }
}

// Create new todo
async function createTodo(title, description) {
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ title, description }),
        });
        if (response.ok) {
            fetchTodos();
            return true;
        }
        return false;
    } catch (error) {
        console.error('Error creating todo:', error);
        return false;
    }
}

// Toggle todo completion
async function toggleTodo(id, title, description, completed) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                title,
                description,
                completed: !completed
            }),
        });
        if (response.ok) {
            fetchTodos();
        }
    } catch (error) {
        console.error('Error toggling todo:', error);
    }
}

// Edit todo
async function editTodo(id, title, description, completed) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ 
                title, 
                description,
                completed
            }),
        });
        if (response.ok) {
            fetchTodos();
            return true;
        }
        return false;
    } catch (error) {
        console.error('Error editing todo:', error);
        return false;
    }
}

// Delete todo
async function deleteTodo(id) {
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'DELETE',
        });
        if (response.ok) {
            fetchTodos();
        }
    } catch (error) {
        console.error('Error deleting todo:', error);
    }
}

// Switch to edit mode
function switchToEditMode(todoElement, id, title, description, completed) {
    const contentDiv = todoElement.querySelector('.todo-content');
    const actionsDiv = todoElement.querySelector('.todo-actions');

    // Create edit form
    contentDiv.innerHTML = `
        <input type="text" class="edit-input" id="edit-title-${id}" value="${title}" />
        <textarea class="edit-input description-textarea" id="edit-description-${id}">${description}</textarea>
    `;

    // Update actions
    actionsDiv.innerHTML = `
        <button class="save-btn" data-id="${id}" data-completed="${completed}">Save</button>
        <button class="cancel-btn">Cancel</button>
    `;
}

// Render todos in the DOM
function renderTodos(todos) {
    todoList.innerHTML = '';
    todos.forEach(todo => {
        const todoElement = document.createElement('div');
        todoElement.className = `todo-item ${todo.completed ? 'completed' : ''}`;
        
        todoElement.innerHTML = `
            <div class="todo-content">
                <div class="todo-title">${todo.title}</div>
                <div class="todo-description">${todo.description.replace(/</g, '&lt;').replace(/>/g, '&gt;')}</div>
            </div>
            <div class="todo-actions">
                <button class="toggle-btn" data-id="${todo.id}" data-title="${todo.title}" data-description="${todo.description}" data-completed="${todo.completed}">
                    ${todo.completed ? 'Undo' : 'Complete'}
                </button>
                <button class="edit-btn" data-id="${todo.id}" data-title="${todo.title}" data-description="${todo.description}" data-completed="${todo.completed}">
                    Edit
                </button>
                <button class="delete-btn" data-id="${todo.id}">Delete</button>
            </div>
        `;
        
        todoList.appendChild(todoElement);
    });
}

// Form submit handler
todoForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const titleInput = document.getElementById('title');
    const descriptionInput = document.getElementById('description');
    
    const success = await createTodo(titleInput.value, descriptionInput.value);
    
    if (success) {
        titleInput.value = '';
        descriptionInput.value = '';
    }
});

// Add event delegation for todo actions
todoList.addEventListener('click', async (e) => {
    const button = e.target;
    const todoItem = button.closest('.todo-item');

    if (button.classList.contains('edit-btn')) {
        switchToEditMode(
            todoItem,
            button.dataset.id,
            button.dataset.title,
            button.dataset.description,
            button.dataset.completed === 'true'
        );
    } else if (button.classList.contains('save-btn')) {
        const id = button.dataset.id;
        const completed = button.dataset.completed === 'true';
        const titleInput = document.getElementById(`edit-title-${id}`);
        const descriptionInput = document.getElementById(`edit-description-${id}`);
        
        if (titleInput && descriptionInput) {
            const success = await editTodo(
                id,
                titleInput.value,
                descriptionInput.value,
                completed
            );
            if (success) {
                fetchTodos();
            }
        }
    } else if (button.classList.contains('cancel-btn')) {
        fetchTodos();
    } else if (button.classList.contains('toggle-btn')) {
        const { id, title, description, completed } = button.dataset;
        await toggleTodo(id, title, description, completed === 'true');
    } else if (button.classList.contains('delete-btn')) {
        await deleteTodo(button.dataset.id);
    }
});

// Initial load
fetchTodos();
