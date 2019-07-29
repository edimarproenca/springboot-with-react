import axios from 'axios'

const COURSE_API_URL = 'http://localhost:8081'
const CAR_API_URL = `${COURSE_API_URL}/cars`

class CourseDataService {

    retrieveAllCourses() {
        return axios.get(`${CAR_API_URL}/findAll`);
    }

    populateDataBase(){
        return axios.post(`${CAR_API_URL}/loadDataBase`)
    }

    delete(e){
        return axios.delete(`${CAR_API_URL}/delete/`, 
                {
                    params: {
                    id: e.id
                }
        })
    }
}

export default new CourseDataService()