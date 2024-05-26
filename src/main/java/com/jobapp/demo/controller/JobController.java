package com.jobapp.demo.controller;

import com.jobapp.demo.model.JobPost;
import com.jobapp.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    //home page
    @GetMapping("home")
    public String home() {
        return "home";
    }

    //add job
    @GetMapping("addjob")
    public String addJob() {
        return "addjob";
    }

    //success page for add job
    @GetMapping("success")
    public String successpage() {
        return "success";
    }

    //view job
    @GetMapping("viewalljobs")
    public String viewAllJobs(Model model) {
        List<JobPost> jobs = jobService.getAllJobs();
        model.addAttribute("jobPosts", jobs);
        return "viewalljobs";
    }

    //handle submit form
    @PostMapping("handleForm")
    public String handleForm(JobPost jobPost) throws Exception {
        jobService.addJob(jobPost);
        return "success";
    }

}
