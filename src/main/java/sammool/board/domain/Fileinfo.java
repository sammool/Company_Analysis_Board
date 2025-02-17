package sammool.board.domain;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Fileinfo {
    private Long id;
    private String originalFilename;
    private String storedFilename;
    private String uploadPath;

    public Fileinfo(){

    }
    public Fileinfo(String originalFilename, String storedFilename, String uploadPath){
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.uploadPath = uploadPath;
    }
}
