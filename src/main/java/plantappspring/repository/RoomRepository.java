package plantappspring.repository;

import plantappspring.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // Query by humidity level
    List<Room> findByHumidityLevel(String humidityLevel);

    // Query by light level
    List<Room> findByLightLevel(String lightLevel);

    // Query by window orientation
    List<Room> findByWindowFacing(String windowFacing);

    // Query for sorted rooms by name
    List<Room> findAllByOrderByRoomNameAsc();

    Optional<Room> findByRoomName(String roomName);
}