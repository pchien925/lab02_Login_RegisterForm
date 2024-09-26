package ltweb_weekly_proj.pc.configs;

import java.util.Map;
import java.util.HashMap;

import com.cloudinary.Cloudinary;

import ltweb_weekly_proj.pc.utils.Constant;

public class CloudinaryConfig {

	public Cloudinary cloudinary() {
		Map<String, String> config = new HashMap<>();
		config.put("cloud_name", Constant.CLOUD_NAME);
		config.put("api_key", Constant.API_KEY);
		config.put("api_secret", Constant.API_SECRET);

		return new Cloudinary(config);
	}
}
