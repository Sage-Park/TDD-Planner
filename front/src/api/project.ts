import axios from "axios";

interface Project {
    id:number;
    name:string;
}

const config = {headers : {"Content-Type": 'application/json'}};

export const getProjects = () => {

    return axios.get<Project[]>(`${process.env.REACT_APP_BACK_BASE_URL}/projects`)
}

export const createProject = (name:string) => {

    return axios.post(
        `${process.env.REACT_APP_BACK_BASE_URL}/projects`,
        JSON.stringify({name: name}),
        config
    )

}

