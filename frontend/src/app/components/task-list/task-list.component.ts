// task-list.component.ts
import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/service.service';
import { Task, TaskStatus } from '../modules/task.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.scss']
})
export class TaskListComponent implements OnInit {
  todoTasks: Task[] = [];
  inProgressTasks: Task[] = [];
  doneTasks: Task[] = [];
  isLoading = false;
  
  constructor(
    @Inject(TaskService) private taskService: TaskService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.isLoading = true;
    this.taskService.getTasks().subscribe(
      tasks => {
        this.todoTasks = tasks.filter(task => task.status === TaskStatus.TODO);
        this.inProgressTasks = tasks.filter(task => task.status === TaskStatus.IN_PROGRESS);
        this.doneTasks = tasks.filter(task => task.status === TaskStatus.DONE);
        this.isLoading = false;
      },
      error => {
        console.error('Error loading tasks', error);
        this.isLoading = false;
      }
    );
  }

  viewTaskDetails(taskId: string): void {
    this.router.navigate(['/tasks', taskId]);
  }

  createNewTask(): void {
    this.router.navigate(['/tasks/new']);
  }

  // Method to handle drag and drop for status changes
  updateTaskStatus(task: Task, newStatus: TaskStatus): void {
    const updatedTask = { ...task, status: newStatus };
    this.taskService.updateTask(updatedTask).subscribe(
      () => this.loadTasks(),
      error => console.error('Error updating task status', error)
    );
  }
}