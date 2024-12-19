package plantappspring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plantappspring.model.plant.Plant;
import plantappspring.model.room.Room;
import plantappspring.service.PlantService;
import plantappspring.service.RoomService;

import java.util.Optional;
import java.util.Scanner;

@Component
public class PlantAppDemo {

    private final PlantService plantService;
    private final RoomService roomService;

    @Autowired
    public PlantAppDemo(PlantService plantService, RoomService roomService) {
        this.plantService = plantService;
        this.roomService = roomService;
    }

    public void demo() {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input a plant name
        System.out.println("Enter the plant name:");
        String plantName = scanner.nextLine();

        // Add the plant with the retrieved information to the plant table
        Plant plant = plantService.addPlantFromApiData(plantName);

        // Prompt the user to input the name of the room to assign the plant to
        System.out.println("Enter the room name to assign the plant to:");
        String roomName = scanner.nextLine();
        Optional<Room> room = roomService.getRoomByName(roomName);

        // Assign the plant to the specified room
        if (room.isPresent()) {
            plantService.updatePlantWithRoom(plant.getId(), room.get().getId());
        } else {
            System.out.println("Room not found: " + roomName);
        }

        // Print the created plant, its JSON information, and the room it belongs to
        System.out.println("Created Plant: " + plant);
        System.out.println("Plant JSON Information: " + plant.getJsonInfo());
        room.ifPresent(r -> System.out.println("Assigned Room: " + r));
    }
}