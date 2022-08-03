package com.nashtech.rookies.java05.AssetManagement.controller.staff;

import com.nashtech.rookies.java05.AssetManagement.dto.response.AssignmentDetailResponse;
import com.nashtech.rookies.java05.AssetManagement.service.AssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staff/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;
    
    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }
    
    @Operation(summary = "get list assignment have assignDate < current date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get list assignment"),
            @ApiResponse(responseCode = "500", description = "error!")
    })
    @GetMapping()
    public ResponseEntity<Object> getListAssignmentsSortedDate() {
        List<AssignmentDetailResponse> assignmentResponses = this.assignmentService.getListAssignments();
        return ResponseEntity.ok().body(assignmentResponses);
    }
    
    @Operation(summary = "get assignment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get list assignment"),
            @ApiResponse(responseCode = "404", description = "not found assignment!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAssignmentById(@PathVariable long id) {
        return ResponseEntity.ok().body(this.assignmentService.getAssignmentById(id));
    }
}
