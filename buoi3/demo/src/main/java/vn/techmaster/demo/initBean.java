package vn.techmaster.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.techmaster.demo.model.Student;

@Configuration

// Cách thứ 2 để tạo bean. thêm @Bean lên trước constructor và phải đặt trong 1 class co Configuration
public class initBean {
    public initBean(){
        System.out.println("initBean Created");
    }
    @Bean(name = "student")
    public Student student(){
        System.out.println("Tao bean Student tu initBean");
        return new Student("Dung");
    }
    @Bean(name= "student2")
    public Student student2(){
        System.out.println("Tao bean Student2 tu initBean");
        return new Student("haha");
    }
    // Nếu tạo chung tên Bean thì phải thêm tên riêng
}
