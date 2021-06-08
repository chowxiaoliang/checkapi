package redisson;

import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicS {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(BasicS.class);

        RedissonClient redissonClient = MyRedisson.getRedissonClient();

        RKeys keys = redissonClient.getKeys();
        System.out.println(keys.count());

        RBucket<People> peopleBucket = redissonClient.getBucket("people");
        People people = peopleBucket.get();
        if (people == null){
            people = new People();
            people.setAddress("南山区");
            people.setBankNo("973952256");
            people.setName("zhouliang");
            people.setPhoneNo("15872151893");
        }
        peopleBucket.set(people);

        RList<String> person = redissonClient.getList("name");
        if (person.size() > 0){
            person.clear();
        }
        person.add("21");
        person.add("zhouliang");
        person.add("15872151893");
        person.add("南山区");

        for (Object str : person){
            System.out.println(str);
        }

        System.out.println(person.size());

        System.exit(1);
    }
}
