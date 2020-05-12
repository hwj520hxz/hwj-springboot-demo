import com.hwj.demo.user.base.entity.Application
import com.hwj.demo.user.base.entity.User
import com.hwj.demo.user.server.web.dto.ApplicationDTO
import com.hwj.demo.user.server.web.dto.UserDTO

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

    mapper {
        from UserDTO to User converter { UserDTO dto, User user ->
            user.recId = dto.recId
            user.code = dto.code
            user.name = dto.name
            user.password = dto.password
            user.mobile = dto.mobile
            user.email = dto.email
            user.enabled = dto.enabled
            user.internal = dto.internal
            user.recCreatedBy = dto.recCreatedBy
            user.recCreatedOrg = dto.recCreatedOrg
            user.recCreatedTime = dto.recCreatedTime
            user.recModifiedBy = dto.recModifiedBy
            user.recModifiedOrg = dto.recModifiedOrg
            user.recModifiedTime = dto.recModifiedTime
            user.recVersion = dto.recVersion
        }
    }

}

