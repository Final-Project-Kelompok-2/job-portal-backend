package com.lawencon.jobportaladmin.dao;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dto.report.ReportResDto;

@Repository
public class ReportDao extends AbstractJpaDao{
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	public List<ReportResDto> getReport(){
		final List<ReportResDto>reportList = new ArrayList<>();
		final StringBuilder sql = new StringBuilder();
				sql.append("select  ")
				.append( "	tcp.fullname , ")
				.append( "	tj.job_name , ")
//				.append( "	to_char(th.created_at  	- ta.created_at,'DD:HH') as time, ")
				.append(" ta.created_at as apply, ")
				.append(" th.created_at as hired, ")
				.append( "	tet.employment_type_name  ")
				.append( "from t_hired th  ")
				.append( "inner join t_applicant ta  ")
				.append( "on	ta.id = th.applicant_id  ")
				.append( "inner join t_candidate_user tcu  ")
				.append( "on tcu.id = ta.candidate_id  ")
				.append( "inner join t_candidate_profile tcp  ")
				.append( "on tcp.id  = tcu.profile_id  ")
				.append( "inner join t_job tj ")
				.append( "on ta.job_id  = tj.id  ")
				.append( "inner join t_employment_type tet  ")
				.append( "on tet.id = tj.employment_type_id ")
				.append( "where ")
				.append(" 1 = 1 ");
//		if(candidateName != null && "".equalsIgnoreCase("")) {
//			sql.append(" AND tcp.fullname ILIKE :fullName || % ");
//		}
//		if(jobName != null && "".equalsIgnoreCase("")) {
//			sql.append(" AND tj.job_name ILIKE :jobName || % ");
//		}
//		if(employmentTypeName != null && "".equalsIgnoreCase("")) {
//			sql.append(" AND tet.employment_type_name ILIKE :type || % ");
//		}
		final List<?> reportObjs = em().createNativeQuery(sql.toString()).getResultList();
		if(reportObjs.size() > 0) {
			for(Object reportObj : reportObjs) {
				final ReportResDto reports = new ReportResDto();
				final Object[] reportArr = (Object[]) reportObj;
				reports.setFullName(reportArr[0].toString());
				reports.setJobName(reportArr[1].toString());
				reports.setApplyAt(Timestamp.valueOf(reportArr[2].toString()).toLocalDateTime());
				reports.setHiredAt(Timestamp.valueOf(reportArr[3].toString()).toLocalDateTime());				
				reports.setTimeDifference(reports.getApplyAt().until(reports.getHiredAt(), ChronoUnit.DAYS));
				reports.setEmploymentTypeName(reportArr[4].toString());
				reportList.add(reports);
			}
		}
		return reportList;
		
	}

}
