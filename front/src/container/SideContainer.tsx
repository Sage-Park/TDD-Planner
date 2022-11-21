import {useAppSelector} from "../hooks/redux";
import {selectProjects} from "../modules/projectSlice";

export default function SideContainer() {

    const projects = useAppSelector(selectProjects);
    console.log(projects);

    return (
        <>
            <div className="border-r border-gray-200 absolute w-56 h-full p-5">
                <div className="text-gray-600">
                    프로젝트
                </div>
                <div className="px-3 py-1">
                    {projects.entities.map(project => (
                        <div>{project.name}</div>
                    ))}
                </div>
            </div>
        </>
    )

}
