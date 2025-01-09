import com.bantanger.codegen.creator.StudentCreator;
import com.bantanger.codegen.mapper.StudentMapper;
import com.bantanger.codegen.request.StudentCreateRequest;
import org.junit.jupiter.api.Test;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/9
 */
public class ApiTest {

    @Test
    public void test() {
        StudentCreateRequest studentCreateRequest = new StudentCreateRequest();
        studentCreateRequest.setName("name");
        StudentCreator studentCreator = StudentMapper.INSTANCE.request2Dto(studentCreateRequest);
        System.out.println(studentCreator);
    }

}
