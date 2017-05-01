package candidateevaluation

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/candidate/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
