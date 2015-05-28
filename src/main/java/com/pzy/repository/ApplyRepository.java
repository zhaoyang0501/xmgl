package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Apply;
import com.pzy.entity.User;
public interface ApplyRepository extends PagingAndSortingRepository<Apply, Long>,JpaSpecificationExecutor<Apply>{
	public List<Apply> findByUserAndState(User user,String state);
}

