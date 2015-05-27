package com.pzy.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Project;
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>,JpaSpecificationExecutor<Project>{
}

