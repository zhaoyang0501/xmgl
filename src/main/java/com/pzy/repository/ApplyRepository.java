package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Apply;
import com.pzy.entity.Project;
import com.pzy.entity.User;
public interface ApplyRepository extends PagingAndSortingRepository<Apply, Long>,JpaSpecificationExecutor<Apply>{
	public List<Apply> findByUserAndState(User user,String state);
	@Query("select count(*) from Apply a where a.project = ?1") 
	public Integer countAll(Project project);
	@Query("select count(*) from Apply a where a.project = ?1 and (a.state='已申请待审核' or a.state='' or a.state='申请退回' or a.state='申请通过')") 
	public Integer countApplyed(Project project);
	@Query("select count(*) from Apply a where a.project = ?1 and (a.state='已提交验收' or a.state='验收通过' or a.state='验收不通过')") 
	public Integer countCHecked(Project project);
}

