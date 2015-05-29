
package com.pzy.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pzy.entity.Project;
import com.pzy.repository.ApplyRepository;
import com.pzy.repository.ProjectRepository;

@Service
public class ProjectService {
     @Autowired
     private ProjectRepository projectRepository;
     @Autowired
     private ApplyRepository applyRepository;
     @Autowired
     private ApplyService applyService;
     public Page<Project> findAll(final int pageNumber, final int pageSize,final String name){
               PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
              
               Specification<Project> spec = new Specification<Project>() {
                    @Override
                    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (name != null) {
                         predicate.getExpressions().add(cb.like(root.get("name").as(String.class), "%"+name+"%"));
                    }
                    return predicate;
                    }
               };
               Page<Project> result = (Page<Project>) projectRepository.findAll(spec, pageRequest);
               warpCount(result.getContent());
               return result;
     }
     private void warpCount(List<Project> projects){
    	 for(Project bean:projects){
    		 bean.setCount1(applyService.countAll(bean));
    		 bean.setCount2(applyRepository.countApplyed(bean));
    		 bean.setCount3(applyRepository.countCHecked(bean));
    	 }
     }
     public List<Project> findAll(){
    	 return (List<Project>) this.projectRepository.findAll();
     }
     public void delete(Long id){
    	 projectRepository.delete(id);
     }
     public Project find(Long id){
    	  return projectRepository.findOne(id);
     }
     public void save(Project project){
    	 projectRepository.save(project);
     }
}