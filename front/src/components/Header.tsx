import {Link} from "react-router-dom";

export default function Header() {

    return (
        <header className="w-full flex p-4 border-b border-gray-200">
            <h1 className="font-bold">
                <Link to="/">TDD-Planner</Link>
            </h1>
        </header>
    )

}
