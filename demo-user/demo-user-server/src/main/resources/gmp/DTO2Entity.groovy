import com.hwj.demo.user.base.entity.Application
import com.hwj.demo.user.server.web.dto.ApplicationDTO

mappers {
    mapper {
        from ApplicationDTO to Application converter { ApplicationDTO dto, Application application ->
            application.recId = dto.recId
            application.code = dto.code
            application.name = dto.name
            application.description = dto.description
            application.recCreatedBy = dto.recCreatedBy
            application.recCreatedOrg = dto.recCreatedOrg
            application.recCreatedTime = dto.recCreatedTime
            application.recModifiedBy = dto.recModifiedBy
            application.recModifiedOrg = dto.recModifiedOrg
            application.recModifiedTime = dto.recModifiedTime
            application.recVersion = dto.recVersion
        }
    }

}

