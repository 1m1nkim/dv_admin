package deepvue.admin.app.domain.repository.monitoring;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BatchJobInstanceQueryRepository {
    public Map<String, Object> instanceSql(Long jobInstanceId) throws Exception;
}
