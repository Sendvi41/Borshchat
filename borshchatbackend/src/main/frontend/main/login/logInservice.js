import axios from 'axios'


const LOG_REST_API_URL = "http://localhost:8080/consult/authorization";


class LogInService {

    valiadte(name, password){
        const data =  { name: name,
                       password: password};
        return axios.post(LOG_REST_API_URL,data);
    }

}

export default new LogInService()