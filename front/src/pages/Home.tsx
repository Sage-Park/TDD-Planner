import {useEffect, useState} from "react";
import {createProject, getProjects} from "../api/project";
import {Button} from "@mui/material";
import ProjectFormDialog from "../components/ProjectFormDialog";
import Header from "../components/Header";
import SideContainer from "../container/SideContainer";
import {fetchProjects, selectProjects} from "../modules/projectSlice";
import {useAppDispatch, useAppSelector} from "../hooks/redux";

type Project = {
    id: number;
    name: string;
}
const Home = () => {

    const projects = useAppSelector(selectProjects);
    let [modalOpen, setModalOpen] = useState(false);

    const dispatch = useAppDispatch();
    console.log('ok');

    useEffect(() => {

        console.log('useEffect');
        dispatch(fetchProjects());

    }, [])

    const openModal = () => {
        setModalOpen(true);
    }

    const closeModal = () => {
        setModalOpen(false)
    }

    const submitDialog = async (name: string) => {
        let response = await createProject(name);

        closeModal();
    }


    return (
        <>
            <Header/>
            <SideContainer/>
            <div className="pl-56">
                {projects.entities.map((project) => (
                    <div>{project.name}</div>
                ))}
                {projects.entities.length === 0 && (<div>프로젝트가 없습니다.</div>)}
                <div>
                    <Button variant="contained" onClick={openModal}>프로젝트 추가</Button>
                </div>
            </div>
            <ProjectFormDialog open={modalOpen} closeDialog={closeModal} submit={submitDialog}/>
        </>
    );
};

export default Home;
