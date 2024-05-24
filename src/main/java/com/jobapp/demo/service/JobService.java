package com.jobapp.demo.service;

import com.jobapp.demo.model.JobPost;
import com.jobapp.demo.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {

    private JobRepository jobRepository;

    public void addJob(JobPost job) {
        jobRepository.save(job);
    }

    public List<JobPost> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Optional<JobPost>> getJobById(Integer id) throws Exception {
        return Optional.of(jobRepository.findById(id));
    }

    public void updateJob(JobPost jobPost) {
        jobRepository.save(jobPost);
    }

    public void deleteJob(int postId) {
        jobRepository.deleteById(postId);
    }

    public void save() {
        List<JobPost> jobs = new ArrayList<>(List.of(
                new JobPost(1,"MERN Dev","2+ years of experience",2,List.of("React.js","Node.js","MongoDB","Express.js")),
                new JobPost(1,"MEAN Dev","2+ years of experience",2,List.of("Angular","Node.js","MongoDB","Express.js"))
        ));

        jobRepository.saveAll(
                jobs
        );
    }

    public List<JobPost> searchJobByKeyword(String searchQuery) throws Exception{
        List<JobPost> jobs = jobRepository.findByPostProfileContainingOrPostDescContaining(searchQuery, searchQuery);
        if (jobs.isEmpty()) {
            throw new Exception("No jobs found matching the search criteria: " + searchQuery);
        }
        return jobs;
    }
}
