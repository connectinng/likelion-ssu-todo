package likelion.todo.domain.todo.controller;


import likelion.todo.domain.todo.dto.TodoCreateRequestDTO;
import likelion.todo.domain.todo.dto.TodoResponseDTO;
import likelion.todo.domain.todo.dto.TodoReviewRequestDTO;
import likelion.todo.domain.todo.dto.TodoUpdateRequestDTO;
import likelion.todo.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/members/{member_id}/todos")
    public ResponseEntity<TodoResponseDTO> createTodo(
            @PathVariable(name = "member_id") Long memberId,
            @RequestBody TodoCreateRequestDTO req
            ) {
        return ResponseEntity.ok(todoService.createTodo(memberId, req));
    }

    @GetMapping("/members/{member_id}/todos")
    public ResponseEntity<List<TodoResponseDTO>> getTodos(@PathVariable(name = "member_id") Long memberId) {
        return ResponseEntity.ok(todoService.getTodos(memberId));
    }

    @GetMapping("/members/{member_id}/todos/daily")
    public ResponseEntity<List<TodoResponseDTO>> getDailyTodos(
            @PathVariable (name = "member_id") Long memberId,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day
    ) {
        return ResponseEntity.ok(todoService.getDailyTodos(memberId, month, day));
    }

    @PatchMapping("/members/{member_id}/todos/{todoId}")
    public ResponseEntity<TodoResponseDTO> updateTodo(
            @PathVariable (name = "member_id") Long memberId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDTO req
    ) {
        return ResponseEntity.ok(todoService.updateTodo(memberId, todoId, req));
    }

    @DeleteMapping("/members/{member_id}/todos/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable (name = "member_id") Long memberId,
            @PathVariable Long todoId
    ) {
        todoService.deleteTodo(memberId, todoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/members/{member_id}/todos/{todoId}/check")
    public ResponseEntity<TodoResponseDTO> completeTodo(
            @PathVariable (name = "member_id") Long memberId,
            @PathVariable Long todoId
    ) {
        return ResponseEntity.ok(todoService.completeTodo(memberId, todoId));
    }

    @PatchMapping("/members/{member_id}/todos/{todoId}/reviews")
    public ResponseEntity<TodoResponseDTO> reviewTodo(
            @PathVariable (name = "member_id") Long memberId,
            @PathVariable Long todoId,
            @RequestBody TodoReviewRequestDTO req
    ) {
        return ResponseEntity.ok(todoService.reviewTodo(memberId, todoId, req));
    }





}
