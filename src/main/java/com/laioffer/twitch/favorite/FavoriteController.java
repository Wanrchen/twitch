package com.laioffer.twitch.favorite;

import com.laioffer.twitch.db.entity.UserEntity;
import com.laioffer.twitch.external.model.FavoriteResponseBody;
import com.laioffer.twitch.model.TypeGroupedItemList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//发请求过来，对于两个方法来说，使用到body里的信息。
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private  final UserEntity userEntity = new UserEntity(1L,"user0","Foo","Bar","password");

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    @GetMapping
    public TypeGroupedItemList getFavoriteItems(){
        return favoriteService.getGroupedFavoriteItems(userEntity);
    }
    @PostMapping
    public void setFavoriteItem(@RequestBody FavoriteResponseBody body){
            favoriteService.setFavoriteItem(userEntity,body.favorite());
    }
    @DeleteMapping
    public void unsetFavoriteItem(@RequestBody FavoriteResponseBody boby){
        favoriteService.unsetFavoriteItem(userEntity,boby.favorite().twitchId());
    }
}
