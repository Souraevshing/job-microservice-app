package com.jobapp.demo.service;

import com.jobapp.demo.model.JobPost;
import com.jobapp.demo.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {

    private JobRepository jobRepository;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(JobService.class);

    private void validateJobPost(JobPost jobPost) throws Exception {
        if (jobPost == null ||
                jobPost.getPostId() <= 0 ||
                jobPost.getPostProfile() == null ||
                jobPost.getPostDesc() == null ||
                jobPost.getReqExperience() <= 0 ||
                jobPost.getPostTechStack() == null) {
            LOGGER.error("All fields are mandatory, please fill all details!");
            throw new Exception("All fields are mandatory and cannot be null!");
        }
    }

    public void addJob(JobPost jobPost) throws Exception {
        validateJobPost(jobPost);
        jobRepository.save(jobPost);
    }

    public List<JobPost> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<JobPost> getJobById(Integer postId) {
        if (postId == null) {
            LOGGER.error("Post ID cannot be null, failed get job by id!!");
            return Optional.empty();
        }
        Optional<JobPost> job = jobRepository.findById(postId);
        if (job.isEmpty()) {
            LOGGER.error("Job with ID {} does not exist, failed get job by id!", postId);
        }
        return job;
    }

    public void updateJob(JobPost jobPost) throws Exception {
        validateJobPost(jobPost);
        if (jobRepository.findById(jobPost.getPostId()).isEmpty()) {
            LOGGER.error("Job with ID {} does not exist, failed update job!", jobPost.getPostId());
            throw new Exception("Job with ID " + jobPost.getPostId() + " does not exist!");
        }
        jobRepository.save(jobPost);
    }

    public void deleteJob(Integer postId) throws Exception {
        if (postId == null) {
            LOGGER.error("Post ID cannot be null!");
            throw new Exception("Post ID cannot be null, failed delete job by id!!");
        }
        if (jobRepository.findById(postId).isEmpty()) {
            LOGGER.error("Job with ID {} does not exist, failed to delete job by id!", postId);
            throw new Exception("Job with ID " + postId + " does not exist, failed to delete job!");
        }
        jobRepository.deleteById(postId);
    }

    public void save() {
        List<JobPost> jobs = new ArrayList<>(List.of(
                new JobPost(1,"MERN Dev","2+ years of experience",2,List.of("React.js","Node.js","MongoDB","Express.js")),
                new JobPost(1,"MEAN Dev","2+ years of experience",2,List.of("Angular","Node.js","MongoDB","Express.js"))
        ));

        jobRepository.saveAll(jobs);
    }

    public List<JobPost> searchJobByKeyword(String searchQuery) {
        List<JobPost> jobs = jobRepository.findByPostProfileContainingOrPostDescContaining(searchQuery, searchQuery);
        if (jobs.isEmpty()) {
            LOGGER.error("Job does not exist, failed to filter jobs!\n");
            return new ArrayList<>();
        }
        return jobs;
    }
}
