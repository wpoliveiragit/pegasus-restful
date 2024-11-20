package ideias.test.maskdata;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyUser {

	@MaskData
	private String login;

	@MaskData(maskText = "@XXX@")
	private String password;

	private int year;

	private Map<String, Object> map2;

	private List<Object> list2;
	
	private MyUser userB;

}
