Task CLI Application

This is a simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal.You can also view the current inprogress,todo and completed task.

Features
-Add a Task: Add a new task with a description.
-Update a Task: Update the description of an existing task.
-Delete a Task: Remove a task by its ID.
-Update a Task Status: Mark a task as "in progress" or "completed".Intially all the tast will be under "to-do" state
-List Tasks: List all tasks or filter them by status (e.g., todo, in progress, completed).

Clone This into IntellJ

# Adding a new task
task cli: add Buy groceries
# Output: Task added successfully (ID: 1)

# Updating a task
task cli: update 1 Buy groceries and milk
# Output: Task updated successfully (ID: 1)

# Deleting a task
task cli: delete 1
# Output: Task deleted 

# Update a task as in progress
task cli: status update 1 inprogress
# Output: Task marked as in progress (ID: 1)

# Marking a task as completed
task cli: status update 1 completed
# Output: Task marked as done (ID: 1)

# Listing all tasks
task cli: list
# Output: List of all tasks

# Listing tasks by status
task cli: status inprogress
task cli: status completed
task cli: status todo
