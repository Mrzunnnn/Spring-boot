package buoi4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class StreamApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamApiDemoApplication.class, args);

		//c1 : sử dụng implement
		Greeting vietNam =  new VietNam();
		vietNam.sayHello("Dung");

		//C2 : sử dụng anonymous class
		Greeting english = new Greeting() {
			@Override
			public void sayHello(String name) {
				System.out.println("Hello "+ name);
			}
		};
		english.sayHello("Thái");

		//C3 : sử dụng lambda Expression
		Greeting china = (name) ->
			System.out.println("Ní hảo " + name);

		china.sayHello("Vũ");

		List<Integer> nums =new ArrayList<>(List.of(1,2,3,5,2,3,4,5,6));
//		Consumer<Integer> consumer = (el)-> System.out.println(el);
//		nums.sort((n1,n2)->n2-n1);
//		nums.removeIf(num ->num>4);
//		nums.forEach((el)-> System.out.println(el));


		// 1. x2 giá trị của chuỗi
//		nums.stream()
//				.map(num -> num * 2) // Biến đổi các phần tử
//				.forEach(num -

		// 2. Lọc ra số chẵn
//		nums.stream()
//				.filter(num -> num % 2 == 0)
//				.forEach(num -> System.out.println(num));

		// 3. Lấy ra phần tử đầu tiên > 4
//		nums.stream()
//				.filter(num -> num >4)
//				.findFirst() // tìm phần tử đầu tiên
//				.ifPresent(num -> System.out.println(num));

		// 4. Lây danh sách giá trị không trùng nhau
//		nums.stream()
//				.distinct() // dùng để lấy 1 lần giá trị phần tử
//				.forEach(num -> System.out.println(num));

		// 5. Lấy phần tử thứ 3 -> 5
//		nums.stream()
//				.skip(2)
//				.limit(3)
//				.forEach(num -> System.out.println(num));

		// 6. Tính tổng phần tử trong list
//		int sum =  nums.stream()
//				.reduce(0,(total,num) -> total + num); // reduce dùng để tính tổng
//		System.out.println(sum);

		// 7. Tính trung bình các phần tử trong list
//		double r =  nums.stream()
//				.mapToInt(n -> Integer.valueOf(n)) // Maptoint là dùng để chuyển đổi dữ liệu về kieu int. sau đó getasdouble là chuyển về double
//				.average().getAsDouble();
//		System.out.println(r);


		// 8. Kiểm tra list có phải là list các con số >0 ?
//		boolean r = nums.stream()
//				.allMatch(num -> num > 0); // dùng để kiểm tra tất cả list có đều thoả mãn đk không?
//		System.out.println(r);
	}


}
