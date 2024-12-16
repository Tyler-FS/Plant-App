// src/main/java/plantappspring/init/RoomInitializerApplication.java
package plantappspring.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import plantappspring.model.room.Room;
import plantappspring.repository.RoomRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "plantappspring.repository")
@EntityScan(basePackages = "plantappspring.model")
public class RoomInitializerApplication {

    private final RoomRepository roomRepository;

    public RoomInitializerApplication(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RoomInitializerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeRooms() {
        return args -> {
            Room room1 = new Room();
            room1.setRoomName("Living Room");
            room1.setHumidityLevel("High");
            room1.setTemperatureLevel("Hot");
            room1.setLightLevel("Medium");
            room1.setWindowFacing("South");
            room1.setNotes("This room is ideal for tropical plants.");

            Room room2 = new Room();
            room2.setRoomName("Sun Room");
            room2.setHumidityLevel("Low");
            room2.setTemperatureLevel("Moderate");
            room2.setLightLevel("Bright");
            room2.setWindowFacing("West");
            room2.setNotes("This room is ideal for desert plants.");

            roomRepository.save(room1);
            roomRepository.save(room2);
        };
    }
}