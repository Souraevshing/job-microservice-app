package com.jobapp.demo.controller;

import com.jobapp.demo.model.JobPost;
import com.jobapp.demo.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobRestController {

    private JobService jobService;

/**
 * initialize database with default jobs
 * */
    @GetMapping("save")
    public void save() {
        jobService.save();
    }

    @GetMapping()
    public List<JobPost> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("{id}")
    public Optional<Optional<JobPost>> getJobById(@PathVariable int id) throws Exception {
        return jobService.getJobById(id);
    }

    @GetMapping("search/{keyword}")
    public List<JobPost> searchJobByKeyword(@PathVariable("keyword") String searchQuery) throws Exception {
        return jobService.searchJobByKeyword(searchQuery);
    }

    @PostMapping()
    public Optional<Optional<JobPost>> createJob(@RequestBody JobPost jobPost) throws Exception {
        jobService.addJob(jobPost);
        return jobService.getJobById(jobPost.getPostId());
    }

    @PutMapping
    public Optional<Optional<JobPost>> updateJob(@RequestBody JobPost jobPost) throws Exception {
        jobService.updateJob(jobPost);
        return jobService.getJobById(jobPost.getPostId());
    }

    @DeleteMapping("{id}")
    public String deleteJob(@PathVariable int id) {
        jobService.deleteJob(id);
        return "Deleted";
    }

}
