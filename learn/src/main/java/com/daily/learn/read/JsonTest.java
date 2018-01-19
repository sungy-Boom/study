package com.daily.learn.read;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by SunGuiyong
 * on 2018/1/19.
 */
public class JsonTest {

    public static void main(String[] args) {
        String str = "{\"errCode\":0,\"data\":{\"targetid\":1992888193,\"display\":1,\"total\":4684,\"reqnum\":50,\"retnum\":50,\"maxid\":\"6360035677753059067\",\"first\":\"6359763281666122200\",\"last\":\"6359741223381138239\",\"hasnext\":true,\"commentid\":[{\"id\":\"6359751032243403864\",\"rootid\":\"0\",\"targetid\":1992888193,\"parent\":\"0\",\"timeDifference\":\"\\u6628\\u5929 21:40:04\",\"time\":1516282804,\"content\":\"\\u9898\\u6750\\u4e0d\\u9519\\uff0c\\u4f46\\u5367\\u5e95\\u7684\\u65f6\\u5019\\u592a\\u5f15\\u4eba\\u77a9\\u76ee\\u4e0d\\u592a\\u597d\\u5427\\uff0c\\u7ed9\\u4eba\\u52a0\\u6df1\\u4e86\\u5370\\u8c61\\u8fd9\\u4e0d\\u662f\\u627e\\u6b7b\\u5417\\uff1f[\\u7591\\u95ee]\",\"title\":\"\",\"up\":\"0\",\"rep\":\"0\",\"type\":\"1\",\"hotscale\":\"0\",\"checktype\":\"1\",\"checkstatus\":\"0\",\"isdeleted\":\"0\",\"tagself\":\"\",\"taghost\":\"\",\"source\":\"9\",\"location\":\"\",\"address\":\"\",\"rank\":\"-1\",\"custom\":\"usertype=0\",\"extend\":{\"at\":0,\"ut\":0,\"ct\":\"\",\"wt\":0},\"orireplynum\":\"0\",\"richtype\":0,\"userid\":\"242310549\",\"poke\":0,\"abstract\":\"\",\"thirdid\":\"pubsource=mobileupdate&msgid=8024027199214&userid=383767736&cfrom=0&scene=0&datakey=cid%3Djhpwng9yy0qms36%26vid%3Dq002570yi7r%26type%3D1%26vtitle%3D%E5%B7%A8%E9%A2%9D%E6%9D%A5%E7%94%B5+%E6%A1%82%E7%BA%B6%E9%95%815%E5%88%86%E9%92%9F%E8%AF%88%E9%AA%974%E4%B8%87%E5%9D%97&seq=8f1370ea-5d5f-4b23-8795-3adbae7a50be&ctrid=0\",\"ispick\":0,\"ishide\":0,\"isauthor\":0,\"replyuser\":\"\",\"replyuserid\":0,\"replyhwvip\":0,\"replyhwlevel\":0,\"replyhwannual\":0,\"userinfo\":{\"userid\":\"242310549\",\"uidex\":\"ec79916facd7ff64e45e0f6b73ef527467\",\"nick\":\"WE\'\\u4e0d\\u6563\",\"head\":\"http:\\/\\/q4.qlogo.cn\\/g?b=qq&k=7VSRKXvyV5yicGGWtdUkc8w&s=40&t=1516291200\",\"gender\":1,\"viptype\":\"0\",\"mediaid\":0,\"region\":\"\\u4e2d\\u56fd:\\u5c71\\u4e1c:\\u9752\\u5c9b\",\"thirdlogin\":0,\"hwvip\":1,\"hwlevel\":4,\"hwannual\":0,\"identity\":\"\",\"wbuserinfo\":[],\"certinfo\":\"\",\"remark\":\"\",\"fnd\":0}}],\"targetinfo\":{\"orgcommentnum\":\"6524\",\"commentnum\":\"4684\",\"checkstatus\":\"0\",\"checktype\":\"1\",\"city\":\"\",\"voteid\":\"12053610\",\"topicids\":\"\",\"commup\":\"12816\"}},\"info\":{\"time\":1516350898}}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject userinfo = data.getJSONObject("userinfo");
        String name = userinfo.getString("nick");
        System.out.println(name);
    }
}
