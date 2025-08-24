const taskList = document.getElementById("task-list");
const taskForm = document.getElementById("task-form");
const taskInput = document.getElementById("task-input");

const API_URL = "http://localhost:8080/tasks";

// Fetch tasks
async function fetchTasks() {
    try {
        const response = await fetch(API_URL);
        const tasks = await response.json();

        taskList.innerHTML = "";

        tasks.forEach(task => {
            const li = document.createElement("li");
            li.textContent = task.title + " - " + task.description;
            taskList.appendChild(li);
        });
    } catch (error) {
        console.error("Error fetching tasks:", error);
    }
}

// Add task
taskForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const newTask = {
        title: taskInput.value,
        description: "No description yet"
    };

    try {
        await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newTask)
        });

        taskInput.value = "";
        fetchTasks();
    } catch (error) {
        console.error("Error adding task:", error);
    }
});

// Initial load
fetchTasks();
