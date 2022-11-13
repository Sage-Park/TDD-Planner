import {useEffect} from "react";
import {getProjects} from "./api/project";

const Home = () => {

  useEffect( () => {

    let projects = getProjects()

    console.log(projects);


  }, [])


  return <div>Home</div>;
};

export default Home;
