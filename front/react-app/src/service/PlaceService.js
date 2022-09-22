import axios from 'axios';

const PLACE_API_BASE_URL = "http://localhost:8080/maps";

class PlaceService{
    getPlaces(){
        return axios.get(PLACE_API_BASE_URL);
    }

    getOnePlace(no){
        return axios.get(PLACE_API_BASE_URL+"/"+no);
    }

}

export default new PlaceService();